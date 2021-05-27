import { Module } from '@nestjs/common';
import { AdminsService } from './admins.service';
import { AdminsController } from './admins.controller';
import { MongooseModule } from '@nestjs/mongoose';
import { Admin, AdminSchema } from './entities/admin.entity';
import {
  Prestamista,
  PrestamistaSchema,
} from 'src/prestamista/entities/prestamista.entity';
import { Rutero, RuteroSchema } from 'src/rutero/entities/rutero.entity';
import { User, UserSchema } from 'src/users/entities/user.entity';

@Module({
  imports: [
    MongooseModule.forFeature([
      { name: Admin.name, schema: AdminSchema },
      { name: Prestamista.name, schema: PrestamistaSchema },
      { name: Rutero.name, schema: RuteroSchema },
      { name: User.name, schema: UserSchema },
    ]),
  ],
  controllers: [AdminsController],
  providers: [AdminsService],
})
export class AdminsModule {}
