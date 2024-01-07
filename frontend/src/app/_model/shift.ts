import {Employee} from "./employee";

export interface Shift {
  idShift?: number,
  shiftStart: string,
  shiftEnd: string,
  employees: Employee[]
}
