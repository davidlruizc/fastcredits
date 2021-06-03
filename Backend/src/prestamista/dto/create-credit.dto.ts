export class CreateCreditDto {
  client: string;
  paymentMethod: string;
  periodicity: number;
  amount: number;
  interest: number;
  fee: number;
}
