import {AssortmentUnit} from "./assortment-unit";
import {AssortmentCategory} from "./assortment-category";
import {AssortmentType} from "./assortment-type";

export interface Assortment {
  idAssortment?: number,
  name: string,
  quantity: number,
  volume: number,
  unit: AssortmentUnit,
  type: AssortmentType,
  category: AssortmentCategory,
}
