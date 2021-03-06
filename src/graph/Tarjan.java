package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

/**
 * http://blog.csdn.net/jokes000/article/details/7538994
 * http://yzmduncan.iteye.com/blog/990580/ http://www.byvoid.com/blog/biconnect/
 * http://www.haogongju.net/art/1543539
 * http://blog.csdn.net/sevenster/article/details/6882225
 * http://www.byvoid.com/blog/scc-tarjan/
 */
// 强连通分量对于有向图，不定强连通
// 点双连通分量，边双连通分量是对于无向连通图
public class Tarjan
// 塔扬 塔尖
{
	public static int									vertexCnt, edgeCnt;										// 点数，边数
	public static int									MAX			= 100;											// 题目中可能的最大点数
	public static LinkedList<Integer>[]					gra;
	public static LinkedList<Integer>[]					graT;
	// 反向图
	// Kosaraju要用到
	public static LinkedList<Integer>[]					quest;
	public static LinkedList<Integer>[]					Component;													// 获得强连通分量结果
	public static LinkedList<Integer>[]					InComponentCC;												// Component
																													// 所有在组分量组
																													// 可能强连通分量，点双连通分量，边双连通分量
	public static LinkedList<Integer>					sta;														// 存储已遍历的结点
	public static LinkedList<Integer>					gabowSta;													// gabow的辅助STACK
																													// 类似于low数组
	public static int									time, ComponentNumber;										// 索引号，强连通分量个数
	public static int									top;
	public static int									bridgeCnt;
	public static int									cutCnt;

	public static Set									cut;
	public static int[]									dfn;														// 深度优先搜索访问次序
	public static int[]									low;														// 能追溯到的最早的次序
	public static int[]									InStack;													// 检查是否在栈中(2为在栈中GREY，1为已访问BLACK，且不在栈中，0为不在)
	public static int[]									father;
	public static int[]									stack;
	public static int[]									p;
	public static int[]									rank;
	public static int[][]								bridge;
	public static HashMap<Integer, LinkedList<Integer>>	unionMap	= new HashMap<Integer, LinkedList<Integer>>();
	public static int[]									ancestor;
	public static int[]									degree;
	public static int[]									outDegree;
	public static int[]									inDegree;

	public static void init()
	{
		MAX = vertexCnt + 1;
		gra = new LinkedList[MAX];
		graT = new LinkedList[MAX];
		quest = new LinkedList[MAX];

		for (int i = 1; i <= vertexCnt; ++i) {
			gra[i] = new LinkedList<Integer>();
			graT[i] = new LinkedList<Integer>();
			quest[i] = new LinkedList<Integer>();
		}
		degree = new int[MAX];
		outDegree = new int[MAX];
		inDegree = new int[MAX];
		refresh();
	}

	public static void refresh()
	{

		time = ComponentNumber = top = bridgeCnt = cutCnt = 0;
		dfn = new int[MAX];
		low = new int[MAX];
		InStack = new int[MAX];
		father = new int[MAX];
		stack = new int[MAX];

		p = new int[MAX];
		rank = new int[MAX];
		ancestor = new int[MAX];
		cut = new HashSet();
		bridge = new int[MAX][2];
		unionMap = new HashMap();

		Component = new LinkedList[MAX];
		InComponentCC = new LinkedList[MAX];
		for (int i = 1; i <= vertexCnt; ++i) {
			Component[i] = new LinkedList<Integer>();
			InComponentCC[i] = new LinkedList<Integer>();
		}
		sta = new LinkedList<Integer>();
		gabowSta = new LinkedList<Integer>();
		makeSet();
	}

	// O(V+E)
	// connected component 连通分量 无向图 求割点与点连通分量
	// 此图已经是无向连通图
	public static void tarjan_CC_V_DFS(int cur)
	{
		InStack[cur] = 2;
		low[cur] = dfn[cur] = ++time;
		sta.push(cur);
		for (int i = 0; i < gra[cur].size(); ++i) {
			int next = gra[cur].get(i);
			if (dfn[next] == 0) {
				father[next] = cur;
				tarjan_CC_V_DFS(next);
				low[cur] = Math.min(low[cur], low[next]);
				if (low[next] >= dfn[cur]) {
					cut.add(cur);
					++ComponentNumber;
					Component[ComponentNumber].push(cur);// 在这里塞入CUR
					InComponentCC[cur].add(ComponentNumber);
					while (!sta.isEmpty()) {
						int j = sta.peek();
						sta.pop();
						InStack[j] = 1;
						Component[ComponentNumber].push(j);
						InComponentCC[j].add(ComponentNumber);
						if (j == next) {
							break;// 仔细回忆一下 为什么这里不用CUR来判断而是用NEXT
							// 不像强连通分量一样用CUR 折磨俺N久的东西呀
							// low[cur] == dfn[cur] 强连通分量是比较CUR与CUR。。。
						}
					}
				}
			}
			// grey 反向边
			else if (InStack[next] == 2 && next != father[cur]) {
				// InStack[next] == 2 && next != father[cur] 完全可以去掉
				// 但next != father[cur]在求桥边与边双边通分支时不能少
				low[cur] = Math.min(low[cur], dfn[next]);
			}
			// BLACK 交叉边 或者正向边 没环
		}
		// 根点
		// 孤立点 其实不算点双连通分支(点重连通分支)

		// if (gra[cur].size() == 0) {
		// // 还需要判断他是否有入度
		// // if(cur...)
		// ++ComponentNumber;
		// System.out.println("ComponentNumber" + ComponentNumber);
		// Component[ComponentNumber].push(cur);
		// InComponentCC[cur].add(ComponentNumber);
		// }

	}

	// O(V+E)
	public static void biconDFS(int cur)
	{
		int a[] = new int[MAX];
		low[cur] = dfn[cur] = ++time;
		stack[top] = cur;
		top++;
		for (int i = 0; i < gra[cur].size(); ++i) {
			int next = gra[cur].get(i);
			if (dfn[next] == 0) // 第一种情况，next是新点
			{
				father[next] = cur;
				biconDFS(next);
				low[cur] = Math.min(low[cur], low[next]);
				if (low[next] >= dfn[cur]) // cur割点(把割点留在栈中)
				{
					cut.add(cur);
					int k = 1;
					a[0] = cur;
					do {
						--top;
						a[k++] = stack[top];
					} while (stack[top] != next);// next 很惊险哟 其实已经把NEXT加进去了
					System.out.println("a:" + Arrays.toString(Arrays.copyOf(a, k)));
				}
			}
			else { // u,w是回边(w是u的祖先)
				if (next != father[cur]) {
					// next != father 完全可以去掉,但求桥边与边双边通分支时不能少
					// father不能用数组， 想想为什么
					low[cur] = Math.min(low[cur], dfn[next]);
				}
			}
		}
		// for (int i = 1; i <= vertexCnt; i++) {
		// if (father[i] == 0) {// root
		// if (gra[i].size() >= 2) {
		// cutCnt++;
		// }
		// }
		// else if (dfn[father[i]] <= low[i]) {
		// cutCnt++;// i为割点 father这个不行
		// }
		// }
		// if( u is root )
		// u是割点 <=> u至少有两个孩子
		// else
		// u是割点 <=> DFN[u]<=LOW[v]
		// */
	}

	// 求割边与边连通分量
	// 此图已经是无向连通图
	public static void tarjan_CC_E_DFS(int cur, int father)
	{
		low[cur] = dfn[cur] = ++time;
		sta.push(cur);
		int repeat = 0;
		for (int i = 0; i < gra[cur].size(); ++i) {
			int next = gra[cur].get(i);
			if (next == father) {
				repeat++;
			}
			// white 正边
			if (dfn[next] == 0) {
				tarjan_CC_E_DFS(next, cur);
				low[cur] = Math.min(low[cur], low[next]);
				if (low[next] > dfn[cur]) // 桥判断没有=
				{
					bridge[++bridgeCnt][0] = cur;
					bridge[bridgeCnt][1] = next;
				}
				else {// 不是桥，缩点
					union(cur, next);
				}
				// if (low[next] >= dfn[cur]) 不能用这个来统计边连通分去 想想为什么？
			}
			// grey 反向边
			else {// next != father[cur] 求桥边与边双边通分支时不能少 考虑平行边的情况
				if (next != father || repeat != 1) {
					low[cur] = Math.min(low[cur], dfn[next]);
				}
			}
			// BLACK 交叉边 或者正向边 没环
		}
	}

	// O(V+E)
	public static void bridge_dfs(int cur, int father)
	{
		int a[] = new int[MAX];
		low[cur] = dfn[cur] = ++time;
		stack[top] = cur;
		top++;
		int repeat = 0;
		for (int i = 0; i < gra[cur].size(); ++i) {
			int next = gra[cur].get(i);
			if (next == father) {
				repeat++;
			}
			if (dfn[next] == 0) // 第一种情况，next是新点
			{
				bridge_dfs(next, cur);
				low[cur] = Math.min(low[cur], low[next]);
				if (low[next] > dfn[cur]) // 桥判断没有=
				{
					bridge[++bridgeCnt][0] = cur;
					bridge[bridgeCnt][1] = next;
				}
				else {// 不是桥，缩点
					union(cur, next);
				}
				// 不能这个来统计 BRIDGE SC。。。 有BUG 想想为什么？
				if (low[next] >= dfn[cur]) {
					int k = 0;
					if (low[next] == dfn[cur]) {
						k++;
						a[0] = cur;
					}
					do {
						--top;
						a[k++] = stack[top];
					} while (stack[top] != next);// next 很惊险哟 其实已经把NEXT加进去了
					System.out.println("bridge SC:" + Arrays.toString(Arrays.copyOf(a, k)));
				}
			}
			else { // u,w是回边(w是u的祖先)
				if (next != father || repeat != 1) {
					// next != father[cur] 求桥边与边双边通分支时不能少 考虑平行边的情况
					low[cur] = Math.min(low[cur], dfn[next]);
				}
			}
		}
	}

	// Strong connected component 强连通分量 有向图
	// 此图不定为强连通图
	// O(V+E)
	public static void tarjan_SCC_DFS(int cur)
	{
		// System.out.println("cur:" + cur);
		InStack[cur] = 2;
		low[cur] = dfn[cur] = ++time;
		sta.push(cur);

		for (int i = 0; i < gra[cur].size(); ++i) {
			int next = gra[cur].get(i);
			// System.out.println("next:" + next);
			if (dfn[next] == 0) {
				tarjan_SCC_DFS(next);
				low[cur] = Math.min(low[cur], low[next]);
			}
			// grey 反向边
			else if (InStack[next] == 2) {
				low[cur] = Math.min(low[cur], dfn[next]);
			}
			// BLACK 交叉边 或者正向边 没环
		}
		// System.out.println("low[" + cur + "]:" + low[cur]);
		// System.out.println("dfn[" + cur + "]:" + dfn[cur]);
		if (low[cur] == dfn[cur]) {
			++ComponentNumber;
			while (!sta.isEmpty()) {
				int j = sta.peek();
				// System.out.println("j:" + j);
				sta.pop();
				InStack[j] = 1;
				Component[ComponentNumber].push(j);
				InComponentCC[j].add(ComponentNumber);
				if (j == cur) {
					System.out.println();
					break;
				}
			}
		}
	}

	// 盖宝
	// http://www.nocow.cn/index.php/Gabow%E7%AE%97%E6%B3%95
	// Gabow算法与Tarjan算法的核心思想实质上是相通的,就是利用强连通分量必定是DFS的一棵子树
	// 这个重要性质,通过找出这个子树的根来求解强分量.具体到实现是利用一个栈S来保存DFS遇到的
	// 所有树边的另一端顶点,在找出强分量子树的根之后,弹出S中的顶点一一进行编号.
	// 二者不同的是,Tarjan算法通过一个low数组来维护各个顶点能到达的最小前序编号,而Gabow算法
	// 通过维护另一个栈来取代low数组,将前序编号值更大的顶点都弹出,然后通过栈顶的那个顶点来判 断是否找到强分量子树的根
	public static void gabow_SCC_DFS(int cur)
	{
		// System.out.println("cur:" + cur);
		InStack[cur] = 2;
		dfn[cur] = ++time;
		sta.push(cur);
		gabowSta.push(cur);
		for (int i = 0; i < gra[cur].size(); ++i) {
			int next = gra[cur].get(i);
			// System.out.println("next:" + next);
			if (dfn[next] == 0) {
				gabow_SCC_DFS(next);
			}
			// grey 反向边
			// 否则如果当前顶点不属于强分量
			else if (InComponentCC[next].size() == 0) {
				// else if (InStack[next] == 2) {// 其实这里用instack也可以的
				while (dfn[gabowSta.peek()] > dfn[next]) {
					// 就将路径栈gabowSta中大于当前顶点dfn值的顶点都弹出
					gabowSta.pop();
				}
			}

			// BLACK 交叉边 或者正向边 没环
		}
		if (gabowSta.peek() == cur) {// 如果gabowSta栈顶元素等于cur,则找到强分量的根,就是cur
			gabowSta.pop();
			++ComponentNumber;
			while (!sta.isEmpty()) {
				int j = sta.peek();
				// System.out.println("j:" + j);
				sta.pop();
				InStack[j] = 1;
				Component[ComponentNumber].push(j);
				InComponentCC[j].add(ComponentNumber);
				if (j == cur) {
					System.out.println();
					break;
				}
			}
		}
	}

	// Strong connected component 强连通分量 有向图
	public static void tarjan_noRecrusiveForSCC(int u)
	{
		InStack[u] = 2;
		low[u] = dfn[u] = ++time;
		sta.push(u);
		while (!sta.isEmpty()) {
			u = sta.peek();
			int t = getAdjUnvisitedVertex(u);
			if (t != -1) {
				sta.push(t);
				InStack[t] = 2;
				low[t] = dfn[t] = ++time;
			}
			else {
				for (int i = 0; i < gra[u].size(); ++i) {
					int x = gra[u].get(i);
					low[u] = Math.min(low[u], low[x]);
				}
				sta.pop();
				InStack[u] = 1;
				Component[ComponentNumber].push(u);
				InComponentCC[u].add(ComponentNumber);
				if (low[u] == dfn[u]) {
					System.out.println("low2[" + u + "]" + low[u]);
					++ComponentNumber;
					System.out.println(ComponentNumber);
				}
			}
		}
	}

	// 离线算法 就是要把所有有QUERY收集完
	// O(V+E+Q)
	private static void tarjan_LCA(int cur)
	{
		ancestor[cur] = cur;
		for (int i = 0; i < gra[cur].size(); ++i) {
			int next = gra[cur].get(i);
			tarjan_LCA(next);
			union(cur, next);
			ancestor[findSet(cur)] = cur;
		}
		InStack[cur] = 2; // BLACK
		for (int i = 0; i < quest[cur].size(); i++) {
			// 如果已经访问了问题节点,就可以返回结果了.
			if (InStack[quest[cur].get(i)] == 2) {
				System.out.println(cur + "-" + quest[cur].get(i) + ":" + ancestor[findSet(quest[cur].get(i))]);
				return;
			}
		}
	}

	private static int getAdjUnvisitedVertex(int cur)
	{
		int next;
		for (int i = 0; i < gra[cur].size(); ++i) {
			next = gra[cur].get(i);
			if (dfn[next] == 0) // ==inStack[t]==0
			{
				return next;
			}
		}
		return -1;
	}

	public static void input()
	{
		int count = 0;
		for (int i = 1; i <= edgeCnt; i++) {
			int a = new Random().nextInt(vertexCnt - 1) + 1;// 1-n
			int b = new Random().nextInt(vertexCnt - 1) + 1;// 1-n
			if (!gra[a].contains(b) && a != b) {
				System.out.println(a + "->" + b);
				gra[a].push(b);
				gra[b].push(a);
				graT[a].push(b);
				graT[b].push(a);
				degree[a]++;
				degree[b]++;
				count++;
			}
		}
		// 去掉了重复了的edge
		edgeCnt = count;

	}

	public static void input2(boolean directedGraph)
	{
		String[] s = { "4-3", "5-6", "5-2", "7-8", "5-7", "3-5", "8-3" };
		// String[] s = { "7-8", "6-1", "8-1", "1-2", "3-2", "1-3", "4-5",
		// "6-7", "5-7", "5-6", "6-8", "7-1" };
		// String[] s = { "1-2", "2-3", "1-4", "3-1", "4-5", "5-6", "6-7", "7-5"
		// };
		// for euler
		// String[] s = { "1-2", "1-3", "2-5", "4-2", "3-2", "4-5" };
		int a, b;
		int count = 0;
		for (int i = 0; i < s.length; i++) {

			a = Integer.valueOf(s[i].split("-")[0]);// 1-n
			b = Integer.valueOf(s[i].split("-")[1]);// 1-n
			if (!gra[a].contains(b) && a != b) {
				System.out.println(a + "-" + b);
				gra[a].push(b);
				graT[b].push(a);
				outDegree[a]++;
				inDegree[b]++;
				if (!directedGraph) {
					gra[b].push(a);
					graT[a].push(b);
					outDegree[b]++;
					inDegree[a]++;
					degree[a]++;
					degree[b]++;
				}
				count++;
			}
		}
		// 去掉了重复了的edge
		edgeCnt = count;

		for (int i = 1; i <= 7; i++) {
			a = new Random().nextInt(vertexCnt - 1) + 1;// 1-n
			b = new Random().nextInt(vertexCnt - 1) + 1;// 1-n
			if (!quest[a].contains(b) && a != b) {
				System.out.println(a + "--" + b);
				quest[a].push(b);
				quest[b].push(a);
			}
		}
		// gra[5].push(6);

	}

	public static void solve(boolean directedGraph)
	{
		if (!directedGraph) {
			refresh();
			bicon();
			printResult("bicon");

			refresh();
			tarjan_CC_V();
			printResult("tarjan_CC_V");

			refresh();
			bridge();
			printResult("bridge_");

			refresh();
			tarjan_CC_E();
			printResult("tarjan_CC_E");
		}
		else {
			refresh();
			tarjan_SCC();
			printResult("tarjan_SCC");

			refresh();
			gabow_SCC();
			printResult("gabow_SCC");

			refresh();
			tarjan_LCA(1);
		}

	}

	// 加degree判定 排除未用点
	public static void tarjan_SCC()
	{
		for (int i = 1; i <= vertexCnt; i++) {
			if (0 == dfn[i]) {
				tarjan_SCC_DFS(i);
			}
		}
	}

	public static void gabow_SCC()
	{
		for (int i = 1; i <= vertexCnt; i++) {
			if (0 == dfn[i]) {
				gabow_SCC_DFS(i);
			}
		}
	}

	public static void tarjan_CC_E()
	{
		for (int i = 1; i <= vertexCnt; i++) {
			if (0 == dfn[i]) {
				tarjan_CC_E_DFS(i, -1);
			}
		}
	}

	public static void bridge()
	{
		for (int i = 1; i <= vertexCnt; i++) {
			if (0 == dfn[i]) {
				bridge_dfs(i, -1);
			}
		}
	}

	public static void tarjan_CC_V()
	{
		for (int i = 1; i <= vertexCnt; i++) {
			if (0 == dfn[i]) {
				tarjan_CC_V_DFS(i);
			}
		}
	}

	public static void bicon()
	{
		for (int i = 1; i <= vertexCnt; i++) {
			if (0 == dfn[i]) {
				biconDFS(i);
			}
		}
	}

	private static void printResult(String type)
	{
		System.out.println();
		System.out.println("****************" + type + "****************");
		if (type == "tarjan_CC_V" || type == "tarjan_SCC" || type == "gabow_SCC") {
			System.out.println("=====Connected Component===========");
			if (ComponentNumber >= 1) {
				System.out.println("Yes");
				for (int i = 1; i <= ComponentNumber; i++) {
					System.out.println(Component[i]);
				}
			}
			else {
				System.out.println("No");
			}

			System.out.println("=====Vetex --> Connected Component Number===========");
			for (int i = 1; i <= vertexCnt; i++) {
				System.out.println(i + " --> " + InComponentCC[i]);
			}
		}
		if (type == "tarjan_CC_V" || type == "bicon") {
			System.out.println("=====Cut Point=" + cutCnt + "==========");
			System.out.println();
			System.out.println("=====Cut Size=" + cut.size() + "==========");
			for (Iterator it = cut.iterator(); it.hasNext();) {
				int t = (Integer) it.next();
				System.out.println(t);
			}
		}
		if (type == "tarjan_CC_E" || type == "bridge") {
			System.out.println("=====Bridge=" + bridgeCnt + "==========");
			for (int i = 1; i <= bridgeCnt; i++) {
				System.out.println(bridge[i][0] + "=" + bridge[i][1]);
			}

			System.out.println("=P[X]========Edge Connected Component===========");
			System.out.println(Arrays.toString(Arrays.copyOf(p, vertexCnt + 1)));
			for (int i = 1; i <= vertexCnt; i++) {
				int pre = findSet(i);
				LinkedList<Integer> l = unionMap.get(pre);
				if (l == null) {
					l = new LinkedList<Integer>();
				}
				l.push(i);
				unionMap.put(pre, l);
			}
			for (Iterator it = unionMap.values().iterator(); it.hasNext();) {
				LinkedList<Integer> l = (LinkedList<Integer>) it.next();
				System.out.println(l);
			}
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		edgeCnt = 22;
		vertexCnt = 9;
		boolean directedGraph = true;
		init();
		input2(directedGraph);
		solve(directedGraph);
	}

	public static void makeSet()
	{
		for (int i = 1; i <= vertexCnt; i++) {
			p[i] = i;
			rank[i] = 1;
		}
	}

	public static int findSet(int x)
	{
		if (x != p[x]) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}

	public static void union(int x, int y)
	{
		int i = findSet(x);
		int j = findSet(y);
		if (rank[i] > rank[j]) {
			p[j] = i;
			rank[i] = rank[i] + rank[j];
		}
		else {
			p[i] = j;
			rank[j] = rank[i] + rank[j];
		}
	}
}