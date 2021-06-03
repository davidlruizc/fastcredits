import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CreateAdminDto } from './dto/create-admin.dto';
import { Admin, AdminDocument } from './entities/admin.entity';
import * as bcrypt from 'bcryptjs';
import { EnablePrestamistaDto } from './dto/enable-prestamista.dto';
import {
  Prestamista,
  PrestamistaDocument,
} from 'src/prestamista/entities/prestamista.entity';
import { Rutero, RuteroDocument } from 'src/rutero/entities/rutero.entity';
import { User, UserDocument } from 'src/users/entities/user.entity';
import { UsersListDto } from './dto/users-list.dto';
import { EnableDisableUsersDto } from './dto/enable-disable-user.dto';

@Injectable()
export class AdminsService {
  constructor(
    @InjectModel(Admin.name) private adminModel: Model<AdminDocument>,
    @InjectModel(Prestamista.name)
    private prestamistaModel: Model<PrestamistaDocument>,
    @InjectModel(Rutero.name) private ruteroModel: Model<RuteroDocument>,
    @InjectModel(User.name) private userModel: Model<UserDocument>,
  ) {}

  async create(createAdminDto: CreateAdminDto) {
    const user = await this.adminModel.findOne({
      email: createAdminDto.email,
    });
    if (user) {
      throw new HttpException(
        'El usuario ingresado ya existe',
        HttpStatus.BAD_REQUEST,
      );
    }
    const password = await bcrypt.hash(createAdminDto.password, 10);
    createAdminDto.password = password;
    const createdUser = new this.adminModel(createAdminDto);
    await createdUser.save();
    return 'El administrador ha sido creado exitosamente';
  }

  async pendingPrestamistas() {
    const prestamistas = await this.prestamistaModel.find({ active: false });
    return prestamistas;
  }

  async allPrestamistas() {
    const prestamistas = await this.prestamistaModel.find({ state: true });
    return prestamistas;
  }

  async enablePrestamista(enablePrestamistaDto: EnablePrestamistaDto) {
    const prestamista = await this.prestamistaModel.findById(
      enablePrestamistaDto.prestamistaId,
    );
    if (prestamista) {
      prestamista.active = true;
      await prestamista.save();
      return 'El se ha activado exitosamente';
    } else {
      throw new HttpException(
        'El prestamista no existe',
        HttpStatus.UNAUTHORIZED,
      );
    }
  }

  async allUsers() {
    const users = await this.userModel.find();
    const ruteros = await this.ruteroModel.find();
    const prestamistas = await this.prestamistaModel.find();
    const list: UsersListDto[] = [];
    users.forEach((user) => {
      list.push({
        id: user.id,
        address: user.address,
        cellphone: user.cellphone,
        country: user.country,
        document: user.document,
        email: user.email,
        gender: user.gender,
        lastname: user.lastname,
        names: user.names,
        state: user.state,
        date: user.date,
        rol: 'Usuario',
      });
    });
    ruteros.forEach((rutero) => {
      list.push({
        id: rutero.id,
        address: rutero.address,
        cellphone: rutero.cellphone,
        country: rutero.country,
        document: rutero.document,
        email: rutero.email,
        gender: rutero.gender,
        lastname: rutero.lastname,
        names: rutero.names,
        state: rutero.state,
        date: rutero.date,
        rol: 'Rutero',
      });
    });
    prestamistas.forEach((prestamista) => {
      list.push({
        id: prestamista.id,
        address: prestamista.address,
        cellphone: prestamista.cellphone,
        country: prestamista.country,
        document: prestamista.document,
        email: prestamista.email,
        gender: prestamista.gender,
        lastname: prestamista.lastname,
        names: prestamista.names,
        state: prestamista.state,
        date: prestamista.date,
        rol: 'Prestamista',
      });
    });
    list.sort(function (a, b) {
      return +new Date(b.date) - +new Date(a.date);
    });
    return list;
  }

  async allUsersAccounts() {
    const users = await this.userModel.find();
    return users;
  }

  async enableDisableUser(enableDisableUsersDto: EnableDisableUsersDto) {
    let user: UserDocument | RuteroDocument | PrestamistaDocument | null = null;
    if (enableDisableUsersDto.rol === 'Usuario') {
      user = await this.userModel.findById(enableDisableUsersDto.userId);
    } else if (enableDisableUsersDto.rol === 'Rutero') {
      user = await this.ruteroModel.findById(enableDisableUsersDto.userId);
    } else if (enableDisableUsersDto.rol === 'Prestamista') {
      user = await this.prestamistaModel.findById(enableDisableUsersDto.userId);
    }
    if (user) {
      user.state = enableDisableUsersDto.state;
      await user.save();
      return `El usuario se ha ${
        enableDisableUsersDto.state ? 'activado' : 'desactivado'
      } exitosamente`;
    } else {
      throw new HttpException('El usuario no existe', HttpStatus.UNAUTHORIZED);
    }
  }
}
