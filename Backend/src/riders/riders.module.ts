import { Module } from '@nestjs/common';
import { RidersService } from './riders.service';
import { RidersController } from './riders.controller';
import { MongooseModule } from '@nestjs/mongoose';
import { Rider, RiderSchema } from './entities/rider.entity';

@Module({
  imports: [
    MongooseModule.forFeature([{ name: Rider.name, schema: RiderSchema }]),
  ],
  controllers: [RidersController],
  providers: [RidersService],
})
export class RidersModule {}
