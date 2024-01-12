import {Animal} from "./animal";
import {Employee} from "./employee";
import {Surgery} from "./surgery";
import {Assortment} from "./assortment";
import {UsedAssortment} from "./used-assortment";

export interface AnimalSurgery {
  idAnimalSurgery?: number,
  animal?: Animal,
  doctor?: Employee,
  surgery?: Surgery,
  date?: string,
  usedAssortment?: UsedAssortment[]
}
