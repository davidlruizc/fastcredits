import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document, SchemaTypes } from 'mongoose';
import { User } from 'src/users/entities/user.entity';

export type OrderDocument = Order & Document;

@Schema()
export class Order {
  @Prop({ type: SchemaTypes.ObjectId, ref: 'User' })
  user: User;

  @Prop({ required: true })
  creationDate: Date;
}

export const OrderSchema = SchemaFactory.createForClass(Order);
