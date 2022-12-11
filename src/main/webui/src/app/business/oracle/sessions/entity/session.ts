export class Session {
    sid!: number;
    name!: string;
    serial!: number;
    state!: string;
    schemaName!: string;
    userName!: string;
    logonTime!: Date;
    program!: string;
    osUser!: string;
    terminal!: string;
    machine!: string;
}