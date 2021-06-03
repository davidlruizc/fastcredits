import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document, Types } from 'mongoose';

export type CreditDocument = Credit & Document;

@Schema()
export class Credit {
  @Prop({ type: Types.ObjectId, ref: 'users' })
  client: Types.ObjectId;

  @Prop({ required: true })
  date: Date;

  @Prop({ required: true })
  paymentMethod: string;

  @Prop({ required: true })
  periodicity: number;

  @Prop({ required: true })
  amount: number;

  @Prop({ required: true })
  interest: number;

  @Prop({ required: true })
  fee: number;
}

export const CreditSchema = SchemaFactory.createForClass(Credit);
