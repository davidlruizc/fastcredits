import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type UserDocument = User & Document;

@Schema()
export class User {
  @Prop({ required: true })
  email: string;

  @Prop({ required: true })
  password: string;

  @Prop({ required: true })
  document: string;

  @Prop({ required: true })
  names: string;

  @Prop({ required: true })
  lastname: string;

  @Prop({ required: true })
  gender: string;

  @Prop({ required: true })
  country: string;

  @Prop({ required: true })
  address: string;

  @Prop({ required: false })
  phone: string;

  @Prop({ required: true })
  cellphone: string;

  @Prop({ required: true })
  date: Date;

  @Prop({ required: true })
  state: boolean;
}

export const UserSchema = SchemaFactory.createForClass(User);
