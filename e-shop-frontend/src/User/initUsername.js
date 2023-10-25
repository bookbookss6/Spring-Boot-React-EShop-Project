import { getUser } from "./getUser";

export async function initUsername(setUsername)
{
    try{
      const user = await getUser();
      if(user !== null)
        setUsername(user.username);
    }
    catch(error)
    {
      console.log(`error: ${error}`);
    }
}