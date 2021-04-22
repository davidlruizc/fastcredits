import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CreateStoreDto } from './dto/create-store.dto';
import { UpdateStoreDto } from './dto/update-store.dto';
import { Store, StoreDocument } from './entities/store.entity';
import * as bcrypt from 'bcryptjs';
import { DeleteImage, UploadImage } from 'src/utils/uploadImage';

@Injectable()
export class StoresService {
  constructor(
    @InjectModel(Store.name) private storeModel: Model<StoreDocument>,
  ) {}

  async create(createStoreDto: CreateStoreDto) {
    const user = await this.storeModel.findOne({ email: createStoreDto.email });
    if (user) {
      throw new HttpException(
        'El usuario ingresado ya existe',
        HttpStatus.BAD_REQUEST,
      );
    }
    const password = await bcrypt.hash(createStoreDto.password, 10);
    createStoreDto.password = password;
    const createdStore = new this.storeModel(createStoreDto);
    await createdStore.save();
    return 'El usuario ha sido creado exitosamente';
  }

  async validate(createStoreDto: CreateStoreDto) {
    const user = await this.storeModel.findOne({ email: createStoreDto.email });
    if (user) {
      const passMatch = await bcrypt.compare(
        createStoreDto.password,
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
    const user = await this.storeModel.findById(id);
    return user;
  }

  async update(id: string, updateStoreDto: UpdateStoreDto) {
    const user = await this.storeModel.findById(id);
    user.name = updateStoreDto.name;
    user.location = updateStoreDto.location;
    if (updateStoreDto.phone) {
      user.phone = updateStoreDto.phone;
    }
    if (updateStoreDto.logo) {
      if (user.logo) {
        try {
          const split = user.logo.split('/');
          await DeleteImage(split[split.length - 1]);
        } catch {}
      }
      try {
        const url = await UploadImage(
          `${user._id}.jpeg`,
          'Stores',
          updateStoreDto.logo,
        );
        user.logo = url;
      } catch {}
    }
    return await user.save();
  }
}
