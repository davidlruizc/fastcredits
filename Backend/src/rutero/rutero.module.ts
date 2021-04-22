import { Module } from '@nestjs/common';
import { RuteroService } from './rutero.service';
import { RuteroController } from './rutero.controller';
import { MongooseModule } from '@nestjs/mongoose';
import { Rutero, RuteroSchema } from './entities/rutero.entity';

@Module({
  imports: [
    MongooseModule.forFeature([{ name: Rutero.name, schema: RuteroSchema }]),
  ],
  controllers: [RuteroController],
  providers: [RuteroService],
})
export class RuteroModule {}
