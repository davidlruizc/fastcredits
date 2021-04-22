import {
  Controller,
  Get,
  Post,
  Body,
  Put,
  Param,
  Delete,
} from '@nestjs/common';
import { RuteroService } from './rutero.service';
import { CreateRuteroDto } from './dto/create-rutero.dto';
import { UpdateRuteroDto } from './dto/update-rutero.dto';

@Controller('rutero')
export class RuteroController {
  constructor(private readonly ruteroService: RuteroService) {}

  @Post('/signUp')
  async create(@Body() createRuteroDto: CreateRuteroDto) {
    const result = await this.ruteroService.create(createRuteroDto);
    return { message: result };
  }

  @Get()
  findAll() {
    return this.ruteroService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.ruteroService.findOne(+id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() updateRuteroDto: UpdateRuteroDto) {
    return this.ruteroService.update(+id, updateRuteroDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.ruteroService.remove(+id);
  }
}
