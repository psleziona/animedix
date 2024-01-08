import {Client} from "./client";
import {Visit} from "./visit";
import {AnimalSurgery} from "./animal-surgery";

export interface Animal {
  idAnimal?: number,
  species: string,
  breed: string,
  name: string,
  dateOfBirth: string,
  owner?: Client,
  visits?: Visit[],
  surgeries?: AnimalSurgery[]
}
