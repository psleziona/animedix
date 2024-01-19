import {AnimalSurgery} from "./animal-surgery";
import {Visit} from "./visit";

export interface Employee {
  id? : number,
  forename: string,
  surname: string,
  phone: string,
  email: string,
  password: string,
  street: string,
  houseNumber: number,
  zipcode: string,
  city: string,
  surgeries? : AnimalSurgery[],
  visits? : Visit[]
}
