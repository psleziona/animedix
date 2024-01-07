import {AssortmentUnit} from "./assortment-unit";
import {AssortmentCategory} from "./assortment-category";

export interface Assortment {
  idAssortment?: number,
  name: string,
  price: number,
  quantity: number,
  unit: AssortmentUnit,
  category: AssortmentCategory
}
