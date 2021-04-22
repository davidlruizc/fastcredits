import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UsersModule } from './users/users.module';
import { CountriesModule } from './countries/countries.module';
import { AdminsModule } from './admins/admins.module';
import { PrestamistaModule } from './prestamista/prestamista.module';
import { RuteroModule } from './rutero/rutero.module';

@Module({
  imports: [
    MongooseModule.forRoot(
      'mongodb+srv://root:0315@cluster0.kn3nr.mongodb.net/FastCredits',
      { useNewUrlParser: true },
    ),
    UsersModule,
    CountriesModule,
    AdminsModule,
    PrestamistaModule,
    RuteroModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
