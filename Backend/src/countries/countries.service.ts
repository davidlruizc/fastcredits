import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CountriesDocument, Country } from './entities/country.entity';

@Injectable()
export class CountriesService {
  constructor(
    @InjectModel(Country.name) private countryModel: Model<CountriesDocument>,
  ) {}

  async findAll() {
    return await this.countryModel.find().sort({ name: 1 });
  }
}
