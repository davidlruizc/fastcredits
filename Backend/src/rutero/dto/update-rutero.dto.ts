import { PartialType } from '@nestjs/mapped-types';
import { CreateRuteroDto } from './create-rutero.dto';

export class UpdateRuteroDto extends PartialType(CreateRuteroDto) {}
