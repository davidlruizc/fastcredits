import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { User, UserDocument } from './entities/user.entity';
import * as bcrypt from 'bcryptjs';
import { CreatePrestamistaDto } from './dto/create-prestamista.dto';
import {
  PrestamistaDocument,
  Prestamista,
} from './entities/prestamista.entity';
import { ValidateUserDto } from './dto/validate-user.dto';

@Injectable()
export class UsersService {
  constructor(
    @InjectModel(User.name) private userModel: Model<UserDocument>,
    @InjectModel(Prestamista.name)
    private prestamistaModel: Model<PrestamistaDocument>,
  ) {}

  async create(createUserDto: CreateUserDto) {
    const user = await this.userModel.findOne({
      username: createUserDto.email,
    });
    if (user) {
      throw new HttpException(
        'El usuario ingresado ya existe',
        HttpStatus.BAD_REQUEST,
      );
    }
    const password = await bcrypt.hash(createUserDto.password, 10);
    createUserDto.password = password;
    const createdUser = new this.userModel({
      ...createUserDto,
      date: new Date(),
      state: true,
    });
    await createdUser.save();
    return 'El usuario ha sido creado exitosamente';
  }

  async createPrestamista(createPrestamistaDto: CreatePrestamistaDto) {
    const user = await this.userModel.findOne({
      username: createPrestamistaDto.email,
    });
    if (user) {
      throw new HttpException(
        'El usuario ingresado ya existe',
        HttpStatus.BAD_REQUEST,
      );
    }
    const password = await bcrypt.hash(createPrestamistaDto.password, 10);
    createPrestamistaDto.password = password;
    const createdPrestimista = new this.prestamistaModel({
      ...createPrestamistaDto,
      date: new Date(),
      state: true,
      active: false,
    });
    await createdPrestimista.save();
    return 'La solicitud del prestamista ha sido enviada satisfactoriamente';
  }

  async validateUser(validateUserDto: ValidateUserDto) {
    const user = await this.userModel.findOne({
      username: validateUserDto.email,
    });
    if (user) {
      const passMatch = await bcrypt.compare(
        validateUserDto.password,
        user.password,
      );
      if (passMatch) {
        return user._id;
      } else {
        throw new HttpException(
          'La contraseña ingresada es incorrecta',
          HttpStatus.UNAUTHORIZED,
        );
      }
    } else {
      throw new HttpException(
        'El usuario ingresado no existe',
        HttpStatus.UNAUTHORIZED,
      );
    }
  }

  async validatePrestamista(validateUserDto: ValidateUserDto) {
    const user = await this.prestamistaModel.findOne({
      username: validateUserDto.email,
    });
    if (user) {
      const passMatch = await bcrypt.compare(
        validateUserDto.password,
        user.password,
      );
      if (passMatch) {
        if (user.active) {
          return user._id;
        } else {
          throw new HttpException(
            'El administrador aún no ha aprobado tu solicitud de creación de cuenta.',
            HttpStatus.UNAUTHORIZED,
          );
        }
      } else {
        throw new HttpException(
          'La contraseña ingresada es incorrecta',
          HttpStatus.UNAUTHORIZED,
        );
      }
    } else {
      throw new HttpException(
        'El usuario ingresado no existe',
        HttpStatus.UNAUTHORIZED,
      );
    }
  }

  async getInformation(id: string) {
    const user = await this.userModel.findById(id);
    return user;
  }

  async update(id: string, updateUserDto: UpdateUserDto) {
    const user = await this.userModel.findById(id);
    return await user.save();
  }
}
