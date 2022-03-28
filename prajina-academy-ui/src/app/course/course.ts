import { CourseModule } from "../course-module/course-module";


export interface Course {
    id?: number,
    name: string,
    description: string,
    version: string,
    status: string,
    contact: string,
    modules: CourseModule[]
}
