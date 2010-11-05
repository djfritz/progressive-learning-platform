
module inferred_rom(clka, clkb, ena, enb, addra, addrb, doa, dob);
        input clka, clkb;
        input ena, enb;
        input [8:0] addra, addrb;
        output reg [31:0] doa, dob;
        reg [31:0] RAM [511:0];

        always @(posedge clka) begin
                if (ena) begin
                        doa <= RAM[addra];
                end
        end

        always @(posedge clkb) begin
                if (enb) begin
                        dob <= RAM[addrb];
                end
        end

        initial begin
		RAM[0] = 32'h08000036;
		RAM[1] = 32'h00000000;
		RAM[2] = 32'h504c502d;
		RAM[3] = 32'h30000000;
		RAM[4] = 32'h3c089000;
		RAM[5] = 32'had000000;
		RAM[6] = 32'h8d090000;
		RAM[7] = 32'h0124502a;
		RAM[8] = 32'h1540fffd;
		RAM[9] = 32'h00000000;
		RAM[10] = 32'h03e00008;
		RAM[11] = 32'h00000000;
		RAM[12] = 32'h3c084000;
		RAM[13] = 32'h03e00008;
		RAM[14] = 32'had040000;
		RAM[15] = 32'h3c084000;
		RAM[16] = 32'h03e00008;
		RAM[17] = 32'h8d020000;
		RAM[18] = 32'h3c083000;
		RAM[19] = 32'h03e00008;
		RAM[20] = 32'h8d020000;
		RAM[21] = 32'h3c082000;
		RAM[22] = 32'h8d090004;
		RAM[23] = 32'h31290002;
		RAM[24] = 32'h1009fffd;
		RAM[25] = 32'h00000000;
		RAM[26] = 32'h8d020008;
		RAM[27] = 32'had090000;
		RAM[28] = 32'h03e00008;
		RAM[29] = 32'h00000000;
		RAM[30] = 32'h3c082000;
		RAM[31] = 32'h8d090004;
		RAM[32] = 32'h31290001;
		RAM[33] = 32'h1009fffd;
		RAM[34] = 32'h00000000;
		RAM[35] = 32'had04000c;
		RAM[36] = 32'had090000;
		RAM[37] = 32'h03e00008;
		RAM[38] = 32'h00000000;
		RAM[39] = 32'h001fc825;
		RAM[40] = 32'h0004c025;
		RAM[41] = 32'h8f040000;
		RAM[42] = 32'h10040009;
		RAM[43] = 32'h00000000;
		RAM[44] = 32'h340f0004;
		RAM[45] = 32'h0c00001e;
		RAM[46] = 32'h21efffff;
		RAM[47] = 32'h00042202;
		RAM[48] = 32'h1404fffb;
		RAM[49] = 32'h00000000;
		RAM[50] = 32'h140ffff6;
		RAM[51] = 32'h23180004;
		RAM[52] = 32'h03200008;
		RAM[53] = 32'h00000000;
		RAM[54] = 32'h3c108000;
		RAM[55] = 32'h8e120004;
		RAM[56] = 32'h001298c2;
		RAM[57] = 32'h3415ff00;
		RAM[58] = 32'h34140010;
		RAM[59] = 32'h00152025;
		RAM[60] = 32'h0c00000c;
		RAM[61] = 32'h2294ffff;
		RAM[62] = 32'h00132025;
		RAM[63] = 32'h0c000004;
		RAM[64] = 32'h0015a842;
		RAM[65] = 32'h1414fff9;
		RAM[66] = 32'h00000000;
		RAM[67] = 32'h0c000027;
		RAM[68] = 32'h3c040000;
		RAM[69] = 32'h34840008;
		RAM[70] = 32'h34040d0a;
		RAM[71] = 32'h0c00001e;
		RAM[72] = 32'h00000000;
		RAM[73] = 32'h00042202;
		RAM[74] = 32'h0c00001e;
		RAM[75] = 32'h00000000;
		RAM[76] = 32'h0c000012;
		RAM[77] = 32'h34100001;
		RAM[78] = 32'h1040ffe7;
		RAM[79] = 32'h10500003;
		RAM[80] = 32'h00000000;
		RAM[81] = 32'h08000036;
		RAM[82] = 32'h00000000;
		RAM[83] = 32'h3c130000;
		RAM[84] = 32'h3673006f;
		RAM[85] = 32'h3c140000;
		RAM[86] = 32'h36940064;
		RAM[87] = 32'h3c150000;
		RAM[88] = 32'h36b5006a;
		RAM[89] = 32'h3c010000;
		RAM[90] = 32'h34210069;
		RAM[91] = 32'h0c000015;
		RAM[92] = 32'h00000000;
		RAM[93] = 32'h00022025;
		RAM[94] = 32'h0c00000c;
		RAM[95] = 32'h00000000;
		RAM[96] = 32'h10220012;
		RAM[97] = 32'h1262001d;
		RAM[98] = 32'h12820022;
		RAM[99] = 32'h12a20027;
		RAM[100] = 32'h00000000;
		RAM[101] = 32'h0800005b;
		RAM[102] = 32'h001fb825;
		RAM[103] = 32'h34160004;
		RAM[104] = 32'h00009025;
		RAM[105] = 32'h00129200;
		RAM[106] = 32'h0c000015;
		RAM[107] = 32'h22d6ffff;
		RAM[108] = 32'h0c00000c;
		RAM[109] = 32'h00022025;
		RAM[110] = 32'h02429025;
		RAM[111] = 32'h1416fff9;
		RAM[112] = 32'h00000000;
		RAM[113] = 32'h02e00008;
		RAM[114] = 32'h00000000;
		RAM[115] = 32'h3c043070;
		RAM[116] = 32'h34846c70;
		RAM[117] = 32'h0c00001e;
		RAM[118] = 32'h00000000;
		RAM[119] = 32'h0c00001e;
		RAM[120] = 32'h00042202;
		RAM[121] = 32'h0c00001e;
		RAM[122] = 32'h00042202;
		RAM[123] = 32'h0c00001e;
		RAM[124] = 32'h00042202;
		RAM[125] = 32'h0800005b;
		RAM[126] = 32'h00000000;
		RAM[127] = 32'h0c000066;
		RAM[128] = 32'h00000000;
		RAM[129] = 32'h00128025;
		RAM[130] = 32'h00128825;
		RAM[131] = 32'h0800005b;
		RAM[132] = 32'h00000000;
		RAM[133] = 32'h0c000066;
		RAM[134] = 32'h00000000;
		RAM[135] = 32'hae320000;
		RAM[136] = 32'h22310004;
		RAM[137] = 32'h0800005b;
		RAM[138] = 32'h00000000;
		RAM[139] = 32'h02000008;
		RAM[140] = 32'h00000000;

	end
endmodule
