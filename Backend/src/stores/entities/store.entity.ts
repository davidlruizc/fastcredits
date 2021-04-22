import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type StoreDocument = Store & Document;

@Schema()
export class StoreLocation {
  @Prop({ required: true })
  address: string;

  @Prop({ required: true })
  latitude: number;

  @Prop({ default: true })
  longitude: number;
}

@Schema()
export class Store {
  @Prop({ required: true })
  email: string;

  @Prop({ required: true })
  password: string;

  @Prop({ default: true })
  enabled: boolean;

  @Prop()
  name: string;

  @Prop()
  logo: string;

  @Prop()
  location: StoreLocation;

  @Prop()
  phone: string;
}

export const StoreSchema = SchemaFactory.createForClass(Store);
