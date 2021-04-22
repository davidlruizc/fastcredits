import {
  Controller,
  Post,
  Body,
  Put,
  Param,
  Get,
  HttpException,
  HttpStatus,
} from '@nestjs/common';
import { UsersService } from './users.service';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { CreatePrestamistaDto } from './dto/create-prestamista.dto';
import { ValidateUserDto } from './dto/validate-user.dto';

@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Post('/signUp')
  async signUp(@Body() createUserDto: CreateUserDto) {
    const result = await this.usersService.create(createUserDto);
    return { message: result };
  }

  @Post('/signUpPrestamista')
  async signUpPrestamista(@Body() createUserDto: CreatePrestamistaDto) {
    const result = await this.usersService.createPrestamista(createUserDto);
    return { message: result };
  }

  @Post('/signIn')
  async signIn(@Body() validateUserDto: ValidateUserDto) {
    let result = null;
    switch (validateUserDto.role) {
      case 0:
        result = await this.usersService.validateUser(validateUserDto);
        break;
      case 1:
        result = await this.usersService.validatePrestamista(validateUserDto);
        break;
      default:
        throw new HttpException(
          'El rol ingresado no existe',
          HttpStatus.BAD_REQUEST,
        );
    }
    return { message: result };
  }

  @Get(':id')
  async getInformation(@Param('id') id: string) {
    const result = await this.usersService.getInformation(id);
    return { data: result };
  }

  @Put(':id')
  async update(@Param('id') id: string, @Body() updateUserDto: UpdateUserDto) {
    const result = await this.usersService.update(id, updateUserDto);
    return { data: result };
  }
}
