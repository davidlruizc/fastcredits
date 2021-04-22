import {
  Controller,
  Get,
  Post,
  Body,
  Put,
  Param,
  Delete,
} from '@nestjs/common';
import { AdminsService } from './admins.service';
import { CreateAdminDto } from './dto/create-admin.dto';
import { EnablePrestamistaDto } from './dto/enable-prestamista.dto';

@Controller('admins')
export class AdminsController {
  constructor(private readonly adminsService: AdminsService) {}

  @Post('/signUp')
  async create(@Body() createAdminDto: CreateAdminDto) {
    return await this.adminsService.create(createAdminDto);
  }

  @Post('/signIn')
  async signIn(@Body() createAdminDto: CreateAdminDto) {
    const result = await this.adminsService.validateUser(createAdminDto);
    return { message: result };
  }

  @Post('/enablePrestamista')
  async enablePrestamista(@Body() enablePrestamistaDto: EnablePrestamistaDto) {
    const result = await this.adminsService.enablePrestamista(
      enablePrestamistaDto,
    );
    return { message: result };
  }
}
