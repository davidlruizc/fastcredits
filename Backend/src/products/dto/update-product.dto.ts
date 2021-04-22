import { PartialType } from '@nestjs/mapped-types';
import { CreateProductDto } from './create-product.dto';

export class UpdateProductDto extends PartialType(CreateProductDto) {
  image?: string;
  name: string;
  measure: string;
  price: number;
  store: string;
}
