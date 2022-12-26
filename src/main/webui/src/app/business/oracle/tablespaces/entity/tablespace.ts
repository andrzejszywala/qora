export class Tablespace {
    tablespaceName!: string;
	blockSize!: number;
	initialExtent!: number;
	nextExtent!: number;
	minExtents!: number;
	maxExtents!: number;
	maxSize!: number;
	pctIncrease!: number;
	minExtlen!: number;
	status!: string;
	contents!: string;
	logging!: string;
	forceLogging!: string;
	extentManagement!: string;
	allocationType!: string;
	pluggedIn!: string;
	segmentSpaceManagement!: string;
	defTabCompression!: string;
	retention!: string;
	bigfile!: string;
	predicateEvaluation!: string;
	encrypted!: string;
	compressFor!: string;	
}