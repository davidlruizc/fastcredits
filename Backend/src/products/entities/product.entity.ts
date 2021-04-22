import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document, SchemaTypes } from 'mongoose';
import { Store } from 'src/stores/entities/store.entity';

export type ProductDocument = Product & Document;

@Schema()
export class Product {
  @Prop({ required: true })
  image: string;

  @Prop({ required: true })
  name: string;

  @Prop({ default: true })
  measure: string;

  @Prop({ default: true })
  price: number;

  @Prop({ default: true })
  active: boolean;

  @Prop({ type: SchemaTypes.ObjectId, ref: 'Store' })
  store: Store;
}

export const ProductSchema = SchemaFactory.createForClass(Product);
