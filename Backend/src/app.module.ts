import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UsersModule } from './users/users.module';
import { CountriesModule } from './countries/countries.module';
import { AdminsModule } from './admins/admins.module';

@Module({
  imports: [
    MongooseModule.forRoot(
      'mongodb+srv://root:0315@cluster0.kn3nr.mongodb.net/FastCredits',
      { useNewUrlParser: true },
    ),
    UsersModule,
    CountriesModule,
    AdminsModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
