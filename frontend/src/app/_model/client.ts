import {Animal} from "./animal";

export interface Client {
  idClient?: number,
  forename: string,
  surname: string,
  phone: string,
  email: string,
  password: string,
  animals: Animal[]
}
