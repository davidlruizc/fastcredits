import {
  Controller,
  Get,
  Post,
  Body,
  Put,
  Param,
  Delete,
} from '@nestjs/common';
import { ProductsService } from './products.service';
import { CreateProductDto } from './dto/create-product.dto';
import { UpdateProductDto } from './dto/update-product.dto';
import { TriggerProductDto } from './dto/trigger-product-dto';

@Controller('products')
export class ProductsController {
  constructor(private readonly productsService: ProductsService) {}

  @Post()
  async create(@Body() createProductDto: CreateProductDto) {
    const result = await this.productsService.create(createProductDto);
    return { message: result };
  }

  @Get('getAll/:id')
  async findAll(@Param('id') id: string) {
    const result = await this.productsService.findAll(id);
    return { data: result };
  }

  @Get(':id')
  async findOne(@Param('id') id: string) {
    const result = await this.productsService.findOne(id);
    return { data: result };
  }

  @Put(':id')
  async update(
    @Param('id') id: string,
    @Body() updateProductDto: UpdateProductDto,
  ) {
    const result = await this.productsService.update(id, updateProductDto);
    return { data: result };
  }

  @Delete(':id')
  async remove(@Param('id') id: string) {
    const result = await this.productsService.remove(id);
    return { message: result };
  }

  @Put('trigger/:id')
  async trigger(
    @Param('id') id: string,
    @Body() triggerProductDto: TriggerProductDto,
  ) {
    const result = await this.productsService.trigger(id, triggerProductDto);
    return { data: result };
  }
}
