import { Controller, Post, Body, Put, Param, Get } from '@nestjs/common';
import { StoresService } from './stores.service';
import { CreateStoreDto } from './dto/create-store.dto';
import { UpdateStoreDto } from './dto/update-store.dto';

@Controller('stores')
export class StoresController {
  constructor(private readonly storesService: StoresService) {}

  @Post('/signUp')
  async signUp(@Body() createStoreDto: CreateStoreDto) {
    const result = await this.storesService.create(createStoreDto);
    return { message: result };
  }

  @Post('/signIn')
  async signIn(@Body() createStoreDto: CreateStoreDto) {
    const result = await this.storesService.validate(createStoreDto);
    return { message: result };
  }

  @Get(':id')
  async getInformation(@Param('id') id: string) {
    const result = await this.storesService.getInformation(id);
    return { data: result };
  }

  @Put(':id')
  async update(
    @Param('id') id: string,
    @Body() updateStoreDto: UpdateStoreDto,
  ) {
    const result = await this.storesService.update(id, updateStoreDto);
    return { data: result };
  }
}
