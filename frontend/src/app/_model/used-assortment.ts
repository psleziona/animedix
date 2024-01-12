import {Assortment} from "./assortment";
import {AnimalSurgery} from "./animal-surgery";

export interface UsedAssortment {
  idUsedAssortment?: number,
  assortment?: Assortment,
  animalSurgery?: AnimalSurgery,
  quantity?: number,
  amount? : number
}
