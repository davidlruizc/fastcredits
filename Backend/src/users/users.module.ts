import { Module } from '@nestjs/common';
import { UsersService } from './users.service';
import { UsersController } from './users.controller';
import { MongooseModule } from '@nestjs/mongoose';
import { User, UserSchema } from './entities/user.entity';
import {
  Prestamista,
  PrestamistaSchema,
} from '../prestamista/entities/prestamista.entity';
import { Admin, AdminSchema } from 'src/admins/entities/admin.entity';
import { Rutero, RuteroSchema } from 'src/rutero/entities/rutero.entity';
import { Credit, CreditSchema } from 'src/prestamista/entities/credit.entity';

@Module({
  imports: [
    MongooseModule.forFeature([
      { name: User.name, schema: UserSchema },
      { name: Prestamista.name, schema: PrestamistaSchema },
      { name: Credit.name, schema: CreditSchema },
      { name: Admin.name, schema: AdminSchema },
      { name: Rutero.name, schema: RuteroSchema },
    ]),
  ],
  controllers: [UsersController],
  providers: [UsersService],
})
export class UsersModule {}
