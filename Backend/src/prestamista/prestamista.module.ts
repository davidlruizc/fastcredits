import { Module } from '@nestjs/common';
import { PrestamistaService } from './prestamista.service';
import { PrestamistaController } from './prestamista.controller';
import { MongooseModule } from '@nestjs/mongoose';
import { User, UserSchema } from 'src/users/entities/user.entity';
import { Prestamista, PrestamistaSchema } from './entities/prestamista.entity';

@Module({
  imports: [
    MongooseModule.forFeature([
      { name: User.name, schema: UserSchema },
      { name: Prestamista.name, schema: PrestamistaSchema },
    ]),
  ],
  controllers: [PrestamistaController],
  providers: [PrestamistaService],
})
export class PrestamistaModule {}
