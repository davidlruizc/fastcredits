import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { Store, StoreDocument } from 'src/stores/entities/store.entity';
import { DeleteImage, UploadImage } from 'src/utils/uploadImage';
import { CreateProductDto } from './dto/create-product.dto';
import { TriggerProductDto } from './dto/trigger-product-dto';
import { UpdateProductDto } from './dto/update-product.dto';
import { Product, ProductDocument } from './entities/product.entity';

@Injectable()
export class ProductsService {
  constructor(
    @InjectModel(Product.name) private productModel: Model<ProductDocument>,
    @InjectModel(Store.name) private storeModel: Model<StoreDocument>,
  ) {}

  async create(createProductDto: CreateProductDto) {
    const createdProduct = new this.productModel(createProductDto);
    createdProduct.active = true;
    if (createProductDto.image) {
      try {
        const url = await UploadImage(
          `${createdProduct._id}.jpeg`,
          'Products',
          createProductDto.image,
        );
        createdProduct.image = url;
      } catch {}
    } else {
      createdProduct.image =
        'https://storage.googleapis.com/tulan-trega/Products/defaultProduct.png';
    }
    await createdProduct.save();
    return 'El producto ha sido creado exitosamente';
  }

  async findAll(id: string) {
    const store = await this.storeModel.findById(id);
    const products = await this.productModel
      .find({ store: store })
      .select({ image: 1, name: 1, measure: 1 });
    return products;
  }

  async findOne(id: string) {
    const product = await this.productModel.findById(id);
    return product;
  }

  async update(id: string, updateProductDto: UpdateProductDto) {
    const product = await this.productModel.findById(id);
    if (updateProductDto.image) {
      try {
        const split = product.image.split('/');
        await DeleteImage(split[split.length - 1]);
      } catch {}
      try {
        const url = await UploadImage(
          `${id}.jpeg`,
          'Products',
          updateProductDto.image,
        );
        product.image = url;
      } catch {}
    }
    product.name = updateProductDto.name;
    product.measure = updateProductDto.measure;
    product.price = updateProductDto.price;
    return await product.save();
  }

  async remove(id: string) {
    await this.productModel.findByIdAndDelete(id);
    return 'El producto ha sido eliminado exitosamente';
  }

  async trigger(id: string, triggerProductDto: TriggerProductDto) {
    const product = await this.productModel.findById(id);
    product.active = triggerProductDto.active;
    return await product.save();
  }
}
