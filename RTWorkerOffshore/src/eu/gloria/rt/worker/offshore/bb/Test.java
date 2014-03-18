package eu.gloria.rt.worker.offshore.bb;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		OffshoreOpInfo info = new OffshoreOpInfo();
		info.getTargetIdList().add("111");
		info.getTargetIdList().add("222");
		info.getOpIdList().add("888");
		info.getOpIdList().add("999");
		System.out.print(info.toJSON());
		OffshoreOpInfo info2 = OffshoreOpInfo.getInfo(info.toJSON());
		
		
		BBGateway cmd = new BBGateway("favor2.info", "8880", "api", false, true);
		
		//cmd.imgList("666");
		//cmd.opInfo("51");
		
		
		//******************* Targets
		String targetId = cmd.targetCreate("M31", "10.6750", "41.2667");
		
		System.out.println("target-create=" + targetId);
		
		
		
		//******************* OPs
		
		String opId = cmd.opCreate(targetId, "1.0", "30", "90", "40", "B", "XXXXXXXXXXXXXXXXXXX1");
		
		System.out.println("op-create=" + opId);
		
		boolean resultOP = cmd.opConfirm(opId);
		System.out.println("op-confirm=" + resultOP);
		
		resultOP = cmd.opCancel(opId);
		System.out.println("op-delete=" + resultOP);
		
		boolean resultTarget = cmd.targetDelete(targetId);
		
		System.out.println("target-delete=" + resultTarget);
		
		
		

	}

}
