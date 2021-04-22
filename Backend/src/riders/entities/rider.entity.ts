import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type RiderDocument = Rider & Document;

@Schema()
export class Rider {
  @Prop({ required: true })
  email: string;

  @Prop({ required: true })
  password: string;

  @Prop({ default: true })
  enabled: boolean;

  @Prop()
  image: string;

  @Prop()
  phone: string;

  @Prop()
  name: string;
}

export const RiderSchema = SchemaFactory.createForClass(Rider);
