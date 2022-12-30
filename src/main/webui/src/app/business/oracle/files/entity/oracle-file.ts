export class OracleFile {
	fileId!: number;
	fileName!: string;
	tablespaceName!: string;
	bytes!: number;
	blocks!: number;
	status!: string;
	relativeFno!: number;
	autoextensible!: string;
	maxbytes!: number;
	maxblocks!: number;
	incrementBy!: number;
	userBytes!: number;
	userBlocks!: number;
}