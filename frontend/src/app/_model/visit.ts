import {Animal} from "./animal";
import {Employee} from "./employee";

export interface Visit {
  idVisit?: number,
  date: string,
  ownerComments: string,
  doctorComments?: string,
  doctorRate?: number,
  animal: Animal,
  doctor: Employee
}
