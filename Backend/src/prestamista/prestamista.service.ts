import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CreatePrestamistaDto } from './dto/create-prestamista.dto';
import {
  Prestamista,
  PrestamistaDocument,
} from './entities/prestamista.entity';
import * as bcrypt from 'bcryptjs';

@Injectable()
export class PrestamistaService {
  constructor(
    @InjectModel(Prestamista.name)
    private prestamistaModel: Model<PrestamistaDocument>,
  ) {}

  async createPrestamista(createPrestamistaDto: CreatePrestamistaDto) {
    const user = await this.prestamistaModel.findOne({
      email: createPrestamistaDto.email,
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
}
