const person = {
  name: "carami",
  job: {
    title: "student",
    manager: {
      name: "kang",
    },
  },
};

function printManagerName(person) {
  console.log(person.job.manager.name);
}

function printManagerName(person) {
  console.log(
    person && person.job && person.job.manager && person.job.manager.name,
  );
}

// ?.
function printManagerName(person) {
  console.log(person?.job?.manager?.name);
}

printManagerName(person);
printManagerName({ name: "carami" });

const kang = {
  name: "kang",
  admin() {
    console.log("관리자입니다.");
  },
};

kang.admin();

const kim = {
  name: "kim",
};

// ?.()
kim.admin?.();

// ?.[]
kang.name;
kang["name"];

console.log(kang?.["name"]);
const hong = {};
console.log(hong?.["name"]);
