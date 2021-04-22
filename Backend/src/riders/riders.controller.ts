import {
  Controller,
  Get,
  Post,
  Body,
  Put,
  Param,
  Delete,
} from '@nestjs/common';
import { RidersService } from './riders.service';
import { CreateRiderDto } from './dto/create-rider.dto';
import { UpdateRiderDto } from './dto/update-rider.dto';

@Controller('riders')
export class RidersController {
  constructor(private readonly ridersService: RidersService) {}

  @Post('/signUp')
  async create(@Body() createRiderDto: CreateRiderDto) {
    const result = await this.ridersService.create(createRiderDto);
    return { message: result };
  }

  @Post('/signIn')
  async signIn(@Body() createRiderDto: CreateRiderDto) {
    const result = await this.ridersService.validate(createRiderDto);
    return { message: result };
  }

  @Get(':id')
  async getInformation(@Param('id') id: string) {
    const result = await this.ridersService.getInformation(id);
    return { data: result };
  }

  @Put(':id')
  async update(
    @Param('id') id: string,
    @Body() updateRiderDto: UpdateRiderDto,
  ) {
    const result = await this.ridersService.update(id, updateRiderDto);
    return { data: result };
  }
}
