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

@Injectable()
export class AdminsService {
  constructor(
    @InjectModel(Admin.name) private adminModel: Model<AdminDocument>,
    @InjectModel(Prestamista.name)
    private prestamistaModel: Model<PrestamistaDocument>,
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
    const prestamistas = await this.prestamistaModel.find();
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
}
