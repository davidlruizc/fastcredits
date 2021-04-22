import { PartialType } from '@nestjs/mapped-types';
import { CreateProductDto } from './create-product.dto';

export class TriggerProductDto extends PartialType(CreateProductDto) {
  active: boolean;
}
