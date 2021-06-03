import { Controller, Get, Post, Body } from '@nestjs/common';
import { AdminsService } from './admins.service';
import { CreateAdminDto } from './dto/create-admin.dto';
import { EnableDisableUsersDto } from './dto/enable-disable-user.dto';
import { EnablePrestamistaDto } from './dto/enable-prestamista.dto';

@Controller('admins')
export class AdminsController {
  constructor(private readonly adminsService: AdminsService) {}

  @Post('/signUp')
  async create(@Body() createAdminDto: CreateAdminDto) {
    return await this.adminsService.create(createAdminDto);
  }

  @Get('/pendingPrestamistas')
  async pendingPrestamistas() {
    const result = await this.adminsService.pendingPrestamistas();
    return { message: result };
  }

  @Get('/allPrestamistas')
  async allPrestamistas() {
    const result = await this.adminsService.allPrestamistas();
    return { message: result };
  }

  @Post('/enablePrestamista')
  async enablePrestamista(@Body() enablePrestamistaDto: EnablePrestamistaDto) {
    const result = await this.adminsService.enablePrestamista(
      enablePrestamistaDto,
    );
    return { message: result };
  }

  @Get('/allUsers')
  async allUsers() {
    const result = await this.adminsService.allUsers();
    return { message: result };
  }

  @Get('/allUsersAccounts')
  async allUsersAccounts() {
    const result = await this.adminsService.allUsersAccounts();
    return { data: result };
  }

  @Post('/enableDisableUser')
  async enableDisableUser(
    @Body() enableDisableUsersDto: EnableDisableUsersDto,
  ) {
    const result = await this.adminsService.enableDisableUser(
      enableDisableUsersDto,
    );
    return { message: result };
  }
}
