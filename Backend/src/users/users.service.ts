import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CreateUserDto } from './dto/create-user.dto';
import { User, UserDocument } from './entities/user.entity';
import * as bcrypt from 'bcryptjs';
import {
  PrestamistaDocument,
  Prestamista,
} from '../prestamista/entities/prestamista.entity';
import { ValidateUserDto } from './dto/validate-user.dto';
import { Admin, AdminDocument } from 'src/admins/entities/admin.entity';
import { Rutero, RuteroDocument } from 'src/rutero/entities/rutero.entity';

@Injectable()
export class UsersService {
  constructor(
    @InjectModel(Prestamista.name)
    private prestamistaModel: Model<PrestamistaDocument>,
    @InjectModel(Admin.name) private adminModel: Model<AdminDocument>,
    @InjectModel(Rutero.name) private ruteroModel: Model<RuteroDocument>,
    @InjectModel(User.name) private userModel: Model<UserDocument>,
  ) {}

  async create(createUserDto: CreateUserDto) {
    const user = await this.userModel.findOne({
      email: createUserDto.email,
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

  async validateUser(validateUserDto: ValidateUserDto) {
    const user = await this.userModel.findOne({
      email: validateUserDto.email,
    });
    if (user) {
      const passMatch = await bcrypt.compare(
        validateUserDto.password,
        user.password,
      );
      if (passMatch) {
        if (!user.state) {
          throw new HttpException(
            'El usuario se encuentra bloqueado.',
            HttpStatus.UNAUTHORIZED,
          );
        }
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
      email: validateUserDto.email,
    });
    if (user) {
      const passMatch = await bcrypt.compare(
        validateUserDto.password,
        user.password,
      );
      if (passMatch) {
        if (!user.state) {
          throw new HttpException(
            'El prestamista se encuentra bloqueado.',
            HttpStatus.UNAUTHORIZED,
          );
        }
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

  async validateAdmin(validateUserDto: ValidateUserDto) {
    const user = await this.adminModel.findOne({
      email: validateUserDto.email,
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

  async validateRutero(validateUserDto: ValidateUserDto) {
    const user = await this.ruteroModel.findOne({
      email: validateUserDto.email,
    });
    if (user) {
      const passMatch = await bcrypt.compare(
        validateUserDto.password,
        user.password,
      );
      if (passMatch) {
        if (!user.state) {
          throw new HttpException(
            'El rutero se encuentra bloqueado.',
            HttpStatus.UNAUTHORIZED,
          );
        }
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
}
