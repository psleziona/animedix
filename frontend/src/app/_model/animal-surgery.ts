import {Animal} from "./animal";
import {Employee} from "./employee";
import {Surgery} from "./surgery";
import {Assortment} from "./assortment";

export interface AnimalSurgery {
  idAnimalSurgery: number,
  animal: Animal,
  doctor: Employee,
  surgery: Surgery,
  date: string,
  usedAssortment: Assortment[]
}
