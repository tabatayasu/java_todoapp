(() => {

    const supabaseUrl = "https://ufaoqtxzqwvagqpkkumk.supabase.co";
    const supabaseKey = "sb_publishable_jannkK8x2jmCgqW5_CIMxQ_RWMNW6tG";


    if (!window._supabaseClient) {
        window._supabaseClient = window.supabase.createClient(supabaseUrl, supabaseKey);
    }


    const client = window._supabaseClient;

    window.addTodo = async function() {
        console.log("追加しようとしました。");
        console.log("タイトル：" + document.getElementById("title").value);
        console.log("内容：" + document.getElementById("description").value);
        console.log("重要度：" + document.getElementById("importance").value);
        console.log("期限：" + document.getElementById("deadline").value);
        await client.from("todo").insert([{
            title: document.getElementById("title").value
        }]);
        loadTodos();
    };

    async function loadTodos() {
        const { data } = await client.from("todo").select("*");
        render(data);
    }

    function render(todos) {
        const list = document.getElementById("todoList");
        list.innerHTML = "";
        todos.forEach(todo => {
            const li = document.createElement("li");
            li.textContent = todo.title;
            list.appendChild(li);
        });
    }

    window.onload = loadTodos;

})();