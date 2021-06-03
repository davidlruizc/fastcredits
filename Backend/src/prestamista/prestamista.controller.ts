import { Controller, Post, Body } from '@nestjs/common';
import { PrestamistaService } from './prestamista.service';
import { CreatePrestamistaDto } from './dto/create-prestamista.dto';
import { CreateCreditDto } from './dto/create-credit.dto';

@Controller('prestamista')
export class PrestamistaController {
  constructor(private readonly prestamistaService: PrestamistaService) {}

  @Post('/signUp')
  async signUpPrestamista(@Body() createUserDto: CreatePrestamistaDto) {
    const result = await this.prestamistaService.createPrestamista(
      createUserDto,
    );
    return { message: result };
  }

  @Post('/createCredit')
  async createCredit(@Body() createCreditDto: CreateCreditDto) {
    const result = await this.prestamistaService.createCredit(createCreditDto);
    return { message: result };
  }
}
