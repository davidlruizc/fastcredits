import { PartialType } from '@nestjs/mapped-types';
import { CreateStoreDto } from './create-store.dto';

export class UpdateStoreLocationDto {
  address: string;
  latitude: number;
  longitude: number;
}

export class UpdateStoreDto extends PartialType(CreateStoreDto) {
  name: string;
  location: UpdateStoreLocationDto;
  logo?: string;
  phone?: string;
}
