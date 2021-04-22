import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CreateRuteroDto } from './dto/create-rutero.dto';
import { UpdateRuteroDto } from './dto/update-rutero.dto';
import { Rutero, RuteroDocument } from './entities/rutero.entity';
import * as bcrypt from 'bcryptjs';

@Injectable()
export class RuteroService {
  constructor(
    @InjectModel(Rutero.name)
    private ruteroModel: Model<RuteroDocument>,
  ) {}

  async create(createRuteroDto: CreateRuteroDto) {
    const user = await this.ruteroModel.findOne({
      email: createRuteroDto.email,
    });
    if (user) {
      throw new HttpException(
        'El rutero ingresado ya existe',
        HttpStatus.BAD_REQUEST,
      );
    }
    const password = await bcrypt.hash(createRuteroDto.password, 10);
    createRuteroDto.password = password;
    const createdPrestimista = new this.ruteroModel({
      ...createRuteroDto,
      date: new Date(),
      state: true,
      active: false,
    });
    await createdPrestimista.save();
    return 'El rutero ha sido creado satisfactoriamente';
  }

  findAll() {
    return `This action returns all rutero`;
  }

  findOne(id: number) {
    return `This action returns a #${id} rutero`;
  }

  update(id: number, updateRuteroDto: UpdateRuteroDto) {
    return `This action updates a #${id} rutero`;
  }

  remove(id: number) {
    return `This action removes a #${id} rutero`;
  }
}
