
export class RollbackSegment {
	segmentName!: string;
	owner!: string;
	tablespaceName!: string;
	segmentId!: number;
	fileId!: number;
	blockId!: number;
	initialExtent!: number;
	nextExtent!: number;
	minExtents!: number;
	maxExtents!: number;
	pctIncrease!: number;
	status!: string;
	instanceNum!: string;
	relativeFno!: string;
}
