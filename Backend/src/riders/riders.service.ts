import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CreateRiderDto } from './dto/create-rider.dto';
import { UpdateRiderDto } from './dto/update-rider.dto';
import { Rider, RiderDocument } from './entities/rider.entity';
import * as bcrypt from 'bcryptjs';
import { DeleteImage, UploadImage } from 'src/utils/uploadImage';

@Injectable()
export class RidersService {
  constructor(
    @InjectModel(Rider.name) private riderModel: Model<RiderDocument>,
  ) {}

  async create(createRiderDto: CreateRiderDto) {
    const user = await this.riderModel.findOne({ email: createRiderDto.email });
    if (user) {
      throw new HttpException(
        'El usuario ingresado ya existe',
        HttpStatus.BAD_REQUEST,
      );
    }
    const password = await bcrypt.hash(createRiderDto.password, 10);
    createRiderDto.password = password;
    const createdStore = new this.riderModel(createRiderDto);
    await createdStore.save();
    return 'El usuario ha sido creado exitosamente';
  }

  async validate(createRiderDto: CreateRiderDto) {
    const user = await this.riderModel.findOne({ email: createRiderDto.email });
    if (user) {
      const passMatch = await bcrypt.compare(
        createRiderDto.password,
        user.password,
      );
      if (passMatch) {
        return user._id;
      } else {
        throw new HttpException(
          'La contraseña ingresada es incorrecta',
          HttpStatus.FORBIDDEN,
        );
      }
    } else {
      throw new HttpException(
        'El usuario ingresado no existe',
        HttpStatus.FORBIDDEN,
      );
    }
  }

  async getInformation(id: string) {
    const user = await this.riderModel.findById(id);
    return user;
  }

  async update(id: string, updateRiderDto: UpdateRiderDto) {
    const user = await this.riderModel.findById(id);
    user.name = updateRiderDto.name;
    user.phone = updateRiderDto.phone;
    if (updateRiderDto.image) {
      if (user.image) {
        try {
          const split = user.image.split('/');
          await DeleteImage(split[split.length - 1]);
        } catch {}
      }
      try {
        const url = await UploadImage(
          `${user._id}.jpeg`,
          'Riders',
          updateRiderDto.image,
        );
        user.image = url;
      } catch {}
    }
    return await user.save();
  }
}
